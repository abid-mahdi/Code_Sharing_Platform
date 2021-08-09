package platform.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import platform.utils.Utils;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
public class CodeInfo {

    @JsonIgnore
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    @Getter
    @Setter
    private String id;

    @JsonProperty
    @Getter
    @Setter
    private String code;

    @JsonProperty
    @Setter
    private String date;

    public String getDate() {
        return date.substring(0,19);
    }

    @JsonProperty("time")
    @Getter
    private Integer timeLeft;

    @JsonIgnore
    private Integer originalTime;

    public void setTime(Integer timeLimit) {
        if (timeLimit <= 0) {
            timeRestricted = false;
            timeLimit = 0;
        }
        originalTime = timeLimit;
        timeLeft = timeLimit;
    }

    @JsonProperty("views")
    @Getter
    private Integer viewsLeft;

    public void setViews(Integer viewLimit) {
        if (viewLimit <= 0) {
            viewRestricted = false;
            viewLimit = 0;
        }
        viewsLeft = viewLimit;
    }

    @JsonIgnore
    @Getter
    @Setter
    private boolean viewRestricted = true;

    @JsonIgnore
    @Getter
    @Setter
    private boolean timeRestricted = true;

    @JsonIgnore
    @Getter
    @Setter
    private boolean enabled = true;

    public void decrementViews() {
        if (viewRestricted) {
            viewsLeft--;
            if (viewsLeft == 0)
                enabled = false;
        }
    }

    public void updateTime() {
        if (timeRestricted && enabled) {
            LocalDateTime endDate = Utils.stringToDate(date).plusSeconds(originalTime);
            timeLeft = Utils.differenceBetweenDates(LocalDateTime.now(), endDate);
            if (timeLeft < 0) {
                enabled = false;
                timeLeft = 0;
            }
        }
    }

}
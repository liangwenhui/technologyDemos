package v2;

import javafx.event.Event;
import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author liangwh
 * tick event
 */
@Data
@ToString
public class Tick  {
    private String id;
    private String name;
    private BigDecimal price;
    private AtomicInteger count = new AtomicInteger(0);




}

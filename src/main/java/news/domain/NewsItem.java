
package news.domain;

import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class NewsItem extends AbstractPersistable<Long> {
    
    @ManyToMany(fetch = FetchType.EAGER)
    private List<Writer> writers;

    @ManyToMany(fetch = FetchType.LAZY)
    private List<Category> categories;
    
    
    private String topic;
    
    private String ingres;
    
    private String text;

    private Date date;
    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] picture;
    
}

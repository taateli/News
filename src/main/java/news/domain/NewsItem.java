
package news.domain;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@DiscriminatorValue(value = "NewsItem")
public class NewsItem extends AbstractPersistable<Long> {
    
    @ManyToMany(mappedBy ="newsItems")
    private List<Writer> writers;

    @ManyToMany(mappedBy ="newsItems")
    private List<Category> categories;
    
    
    private String topic;
    
    
    private String ingres;
    
    private String text;

    private LocalDateTime date;
    
    
    @OneToOne //(fetch = FetchType.EAGER)
    private FileObject picture;
    
}

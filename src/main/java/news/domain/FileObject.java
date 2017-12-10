/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.domain;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Lob;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class FileObject extends AbstractPersistable<Long> {

    @OneToOne
    private NewsItem newsItem;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] content;
    
    private String name;
    private String contentType;
    private long contentLength; 
    
    

}
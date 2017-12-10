
package news.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import news.domain.FileObject;


public interface FileObjectRepository extends JpaRepository<FileObject, Long> {
    
}

package news.service;

import java.io.IOException;
import news.domain.FileObject;
import news.domain.NewsItem;
import news.repository.FileObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileService {
    
    @Autowired
    private FileObjectRepository fileObjectRepository;
    
    public FileObject create(MultipartFile file) throws IOException {
        FileObject fo = new FileObject();
        fo.setName(file.getOriginalFilename());
        fo.setContentType(file.getContentType());
        fo.setContentLength(file.getSize());
        fo.setContent(file.getBytes());
        fileObjectRepository.save(fo);
        return fo;
    }
}

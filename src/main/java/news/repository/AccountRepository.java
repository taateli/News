/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package news.repository;

import news.domain.Account;
import news.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author tatuhelander
 */
public interface AccountRepository extends JpaRepository<Account, Long>{

    public Account findByUsername(String username);
    
}

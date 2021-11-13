package vn.ptit.repositories.order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.order.DigitalWallet;

@Repository
public interface DigitalWalletRepository extends JpaRepository<DigitalWallet, Integer>{

}

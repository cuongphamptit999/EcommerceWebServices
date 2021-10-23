package vn.ptit.repositories.shoes;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.shoes.HighHeels;

@Repository
public interface HighHeelsRepository extends JpaRepository<HighHeels, Integer>{

}

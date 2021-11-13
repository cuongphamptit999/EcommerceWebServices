package vn.ptit.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import vn.ptit.entities.customer.CustomerMember;

@Repository
public interface CustomerMemberRepository extends JpaRepository<CustomerMember, Integer>{

}

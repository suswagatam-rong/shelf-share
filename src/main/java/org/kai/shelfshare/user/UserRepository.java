package org.kai.shelfshare.user;
/*
 * @created 23/06/2024 - 08:51
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

	Optional<User> findByEmail(String email);
}
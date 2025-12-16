package org.kai.shelfshare.user;
/*
 * @created 23/06/2024 - 08:56
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token, Integer> {

	Optional<Token> findByToken(String token);
}
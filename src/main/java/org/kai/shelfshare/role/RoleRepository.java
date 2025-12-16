package org.kai.shelfshare.role;
/*
 * @created 23/06/2024 - 08:49
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	Optional<Role> findByName(String role);
}
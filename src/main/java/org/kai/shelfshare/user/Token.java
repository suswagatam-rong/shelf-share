package org.kai.shelfshare.user;
/*
 * @created 23/06/2024 - 08:52
 * @project Shelf Share
 * @author  Suswagatam Rong
 */

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Token {

	@Id
	@GeneratedValue
	private Integer id;
	private String token;
	private LocalDateTime createdAt;
	private LocalDateTime expiresAt;
	private LocalDateTime validatedAt;

	@ManyToOne
	@JoinColumn(name = "userId", nullable = false)
	private User user;
}
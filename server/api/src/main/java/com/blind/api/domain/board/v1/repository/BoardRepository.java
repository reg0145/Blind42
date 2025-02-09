package com.blind.api.domain.board.v1.repository;

import com.blind.api.domain.board.v1.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BoardRepository extends JpaRepository<Board, Long> {
    Optional<Board> findBoardByName(String name);
}

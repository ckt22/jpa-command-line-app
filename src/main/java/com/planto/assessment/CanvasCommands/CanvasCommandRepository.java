package com.planto.assessment.CanvasCommands;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CanvasCommandRepository extends JpaRepository<CanvasCommand, Integer> {
    
    @Query("SELECT c FROM CanvasCommand c WHERE c.canvas.id = ?1")
    List<CanvasCommand> findCommandsByCanvas(long canvasId);
}

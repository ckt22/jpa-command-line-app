package com.planto.assessment.Canvas;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CanvasRepository extends JpaRepository<Canvas, Integer> {
    
    @Query("SELECT c FROM Canvas c WHERE c.width = :width AND c.height = :height")
    Canvas findByWidthAndHeight(@Param("width") int width, @Param("height") int height);

}

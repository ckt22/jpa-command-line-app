package com.planto.assessment.Canvas;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CanvasService {
    
    @Autowired
    CanvasRepository canvasRepository;

    public Canvas createCanvas(int width, int height) {
        Canvas existingCanvas = canvasRepository.findByWidthAndHeight(width, height);
        if (existingCanvas != null) {
            return existingCanvas;
        }
        Canvas newCanvas = new Canvas(width, height);
        newCanvas.setHeight(height);
        newCanvas.setWidth(width);
        canvasRepository.save(newCanvas);
        System.out.println(newCanvas.toString());
        return newCanvas;
    }
}

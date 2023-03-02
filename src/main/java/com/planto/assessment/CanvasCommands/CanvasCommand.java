package com.planto.assessment.CanvasCommands;

import com.planto.assessment.Canvas.Canvas;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CanvasCommand {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private String command;

    private boolean isUndone = false;

    @ManyToOne
    @JoinColumn(name="canvas_id", nullable=false)
    private Canvas canvas;

    public String getCommand() {
        return this.command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public boolean getIsUndone() {
        return this.isUndone;
    }

    public void setIsUndone(boolean isUndone) {
        this.isUndone = isUndone;
    }

    public void setCanvas(Canvas canvas) {
        this.canvas = canvas;
    }

}

package com.planto.assessment.Canvas;

import java.util.ArrayList;
import java.util.ListIterator;

import com.planto.assessment.CanvasCommands.CanvasCommand;
import com.planto.assessment.CanvasCommands.CanvasCommandService;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="Canvas")
public class Canvas {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    
    private int height;

    private int width;

    public Canvas() {}

    public Canvas(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public long getId() {
        return this.id;
    }

    public int getHeight() {
        return this.height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    // public ArrayList<CanvasCommand> getCanvasCommands() {
    //     return this.canvasCommands;
    // }

    // public void setCanvasCommands(CanvasCommand canvasCommand) {
    //     this.canvasCommands.add(canvasCommand);
    // }
}

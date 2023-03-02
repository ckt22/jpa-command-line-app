package com.planto.assessment.CanvasCommands;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planto.assessment.Canvas.Canvas;
import com.planto.assessment.Canvas.CanvasRepository;

@Service
public class CanvasCommandService {
    
    @Autowired
    CanvasCommandRepository canvasCommandRepository;

    @Autowired
    CanvasRepository canvasRepository;

    private List<CanvasCommand> getCommandsByCavnas(long canvasId) {
        List<CanvasCommand> canvasCommands = new ArrayList<CanvasCommand>();
        canvasCommandRepository.findCommandsByCanvas(canvasId).forEach(command -> canvasCommands.add(command));
        return canvasCommands;
    }

    private List<CanvasCommand> getAllCommands() {
        List<CanvasCommand> canvasCommands = new ArrayList<CanvasCommand>();
        canvasCommandRepository.findAll().forEach(command -> canvasCommands.add(command));
        return canvasCommands;
    }

    public void saveCommand(String command, Canvas canvas) {
        CanvasCommand canvasCommand = new CanvasCommand();
        canvasCommand.setCanvas(canvas);
        canvasCommand.setCommand(command);
        canvasCommand.setIsUndone(false);
        canvasCommandRepository.save(canvasCommand);
    }

    public void undoCommand() {
        List<CanvasCommand> canvasCommands = getAllCommands();
        for (CanvasCommand canvasCommand: canvasCommands) {
            if (!canvasCommand.getIsUndone()) {
                canvasCommand.setIsUndone(true);
                canvasCommandRepository.save(canvasCommand);
                break;
            }
        }
    }

    public void redoCommand() {
        List<CanvasCommand> canvasCommands = getAllCommands();
        for (ListIterator<CanvasCommand> iterator = canvasCommands.listIterator(canvasCommands.size()); iterator.hasPrevious();) {
            CanvasCommand listElement = iterator.previous();
            if (listElement.getIsUndone()) {
                listElement.setIsUndone(false);
                canvasCommandRepository.save(listElement);
                break;
            }
        }
    }

    /**
     * Prints out a new canvas based on the active commands
     * @param height
     * @param width
     */
    public void drawCanvas(int height, int width) {
        char[][] output = new char[height][width];
        // initialize the canvas
        for (int x = 0; x < height; x++) {
            for (int y = 0; y < width; y++) {
                output[x][y] = ' ';
            }
        }
        System.out.println(height);
        Canvas canvas = canvasRepository.findByWidthAndHeight(width, height);
        System.out.println("finding canvas");
        System.out.println(canvas);
        System.out.println(canvas.getId());
        List<CanvasCommand> canvasCommands = this.getCommandsByCavnas(canvas.getId());
        for (CanvasCommand canvasCommand: canvasCommands) {
            if (!canvasCommand.getIsUndone()) {
                String command = canvasCommand.getCommand();
                String[] inputs = command.split(" ");
                System.out.println(inputs);
                int x1 = Integer.parseInt(inputs[1]);
                int y1 = Integer.parseInt(inputs[2]);
                int x2 = Integer.parseInt(inputs[3]);
                int y2 = Integer.parseInt(inputs[4]);
                if (command.startsWith("L")) {
                    if (x1 == x2) {
                        // print horizontal line, fixed row
                        for (int col = y1 - 1; col < y2; col++) {
                            output[col][x1] = 'x';
                        }
                    }
                    if (y1 == y2) {
                        // print vertical line, fixed col
                        for (int row = x1 - 1; row < x2; row++) {
                            output[y1 - 1][row] = 'x';
                        }
                    }
                }
                if (command.startsWith("R")) {
                    for (int row = y1 - 1; row < y2; row ++) {
                        for (int col = x1 - 1; col < x2; col ++) {
                            // first line & last line
                            if (row == y1-1 || row == y2 - 1) {
                                output[row][col] = 'x';
                            }
                            // border
                            if (row > y1-1 && row < y2-1 && (col == x1-1 || col == x2 - 1)) {
                                output[row][col] = 'x';
                            }
                        }
                    }
                }
            }
        }
        // ready to print
        // first line
        System.out.println();
        for (int x = 0; x < width + 2; x ++) {
            System.out.print('-');   
        }
        System.out.println();

        // contents
        for (int x = 0; x < height ; x++) {
            System.out.print('|');
            for (int y = 0; y < width; y++) {
                System.out.print(output[x][y]);
            }
            System.out.print('|');
            System.out.println();
        }

        // last line
        for (int x = 0; x < width + 2; x ++) {
            System.out.print('-');   
        }
        System.out.println();
    }
}

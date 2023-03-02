package com.planto.assessment;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.planto.assessment.Canvas.Canvas;
import com.planto.assessment.Canvas.CanvasRepository;
import com.planto.assessment.Canvas.CanvasService;
import com.planto.assessment.CanvasCommands.CanvasCommandService;

@SpringBootApplication
public class AssessmentApplication implements CommandLineRunner {

	@Autowired
	CanvasCommandService canvasCommandService;

	@Autowired
	CanvasService canvasService;

	public static void main(String[] args) {
		SpringApplication.run(AssessmentApplication.class, args);
	}

	@Override
    public void run(String... args) throws Exception {
		
		Scanner sc = new Scanner(System.in);

		Canvas canvas = null;
		while (true) {
			System.out.print("enter command: ");
			String command = sc.nextLine();

			if (command.equals("Q")) {
				System.out.println("ready to quit application.");
				sc.close();
				System.exit(0);
			}
			if (command.startsWith("C") && canvas == null) {
				String[] inputs = command.split(" ");
				canvas = canvasService.createCanvas(Integer.parseInt(inputs[1]), Integer.parseInt(inputs[2]));
			}
			if (canvas != null && command.equals("Z")) {
				canvasCommandService.undoCommand();
			}
			if (canvas != null && command.equals("X")) {
				canvasCommandService.redoCommand();
			}
			if (canvas != null && command.startsWith("L") || command.startsWith("R")) {
				canvasCommandService.saveCommand(command, canvas);
			}
			// draw the final result
			if (canvas != null) {
				canvasCommandService.drawCanvas(canvas.getHeight(), canvas.getWidth());
			}
		}
    }

}

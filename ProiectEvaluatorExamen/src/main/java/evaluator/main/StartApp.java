package evaluator.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import evaluator.exception.NotAbleToCreateTestException;
import evaluator.model.Intrebare;
import evaluator.model.Statistica;

import evaluator.controller.IntrebariController;
import evaluator.exception.NotAbleToCreateStatisticsException;

//functionalitati
//F01.	 adaugarea unei noi intrebari pentru un anumit domeniu (enunt intrebare, raspuns 1, raspuns 2, raspuns 3, raspunsul corect, domeniul) in setul de intrebari disponibile;
//F02.	 crearea unui nou test (testul va contine 5 intrebari alese aleator din cele disponibile, din domenii diferite);
//F03.	 afisarea unei statistici cu numarul de intrebari organizate pe domenii.

public class StartApp {

    public static void main(String[] args) throws IOException {

        BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

        IntrebariController intrebariController = new IntrebariController();

        boolean activ = true;
        String optiune = null;

        while (activ) {

            System.out.println("");
            System.out.println("1.Adauga intrebare");
            System.out.println("2.Creeaza test");
            System.out.println("3.Statistica");
            System.out.println("4.Exit");
            System.out.println("");

            optiune = console.readLine();

            switch (optiune) {
                case "1":
                    System.out.println("Introduceti enuntul intrebarii : ");
                    String enunt = console.readLine();
                    System.out.println("Introduceti prima varianta de raspuns : ");
                    String varianta1 = console.readLine();
                    System.out.println("Introduceti a doua varianta de raspuns : ");
                    String varianta2 = console.readLine();
                    System.out.println("Introduceti a treia varianta de raspuns : ");
                    String varianta3 = console.readLine();
                    System.out.println("Introduceti varianta corecta ( 1,2,3)");
                    String variantaCorecta = console.readLine();
                    System.out.println("Introduceti domeniul intrebarii : ");
                    String domeniu = console.readLine();
                    try {
                        intrebariController.addNewIntrebare(new Intrebare(enunt, varianta1, varianta2, varianta3, Integer.parseInt(variantaCorecta), domeniu));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "2":
                    try {
                        System.out.println(intrebariController.createNewTest().toString());
                    } catch (NotAbleToCreateTestException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "3":
                    try {
                        System.out.println(intrebariController.getStatistica());
                    } catch (NotAbleToCreateStatisticsException e) {
                        System.out.println(e.getMessage());
                    }

                    break;
                case "4":
                    activ = false;
                    break;
                default:
                    break;
            }
        }

    }

}

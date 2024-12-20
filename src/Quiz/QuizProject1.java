package Quiz;

import java.util.*;
import java.util.Collections;
import java.util.Scanner;
import java.util.Random;

class Participant1{
	/*Scanner sc=new Scanner(System.in);
	String name=sc.nextLine();
	int id=sc.nextInt();
    String place=sc.nextLine();
    String email=sc.nextLine();
    public void setName(String name) {
   	 this.name=name;
    }
    public String getName() {
   	 return name;
    }
    public void setId(int id) {
   	 this.id=id;
    }
    public int getId() {
   	 return id;
    }
    public void setEmail(String email) {
   	 this.email=email;
    }
    public String getEmail() {
   	 return email;
    }
    public void setPlace(String place) {
   	 this.place=place;
    }
    public String getPlace() {
   	 return place;
    }
    }*/}
class Question {
    String questionText;
    String[] options;
    int correctAnswer;
    public Question(String questionText, String[] options, int correctAnswer) {
        this.questionText = questionText;
        this.options = options;
        this.correctAnswer = correctAnswer;
    }
    public void displayQuestion(boolean[] showOptions) {
        System.out.println(questionText);
        for (int i = 0; i < options.length; i++) {
            if (showOptions[i]) {
                System.out.println((i + 1) + ". " + options[i]);
            }
        }
    }
    public boolean isCorrect(int userChoice) {
        return userChoice == correctAnswer;
    }
}
public class QuizProject1  {
    public static void main(String[] args)  {
    	Scanner scanner=new Scanner(System.in);
    	Participant1 p1=new Participant1();
		/*p1.setName("Ume");
		p1.setId(57);
		p1.setEmail("shaiksaheer128@gmail.com");
		p1.setPlace("Madanapalle");	*/
    	System.out.println("WELCOME TO THE QUIZ GAME");
    	System.out.println("Enter the Name");
		String name=scanner.nextLine();
		System.out.println("Enter the Id");
		int id=scanner.nextInt();
		System.out.println("Enter the Place");
	    String place=scanner.next();
	    System.out.println("Enter the Email");
	    String email=scanner.next();
	    

	    System.out.println("/nPlayer Details");
		System.out.println("Name :"+name);
		System.out.println("ID IS:"+id);
		System.out.println("EMAIL IS:"+email);
		System.out.println("PLACE :"+place);
		
		System.out.println("THE PLAYER IS READY TO PLAY THE QUIZ OR NOT");
        String playerchoice=scanner.next();
	    if(playerchoice.equals("YES")) {
			System.out.println("RULES");
			System.out.println("Rule 1:The Player must answer all the questions");
			System.out.println("Rule 2:The Player must remember the lifelines can be use only once");
			System.out.println("Rule 3:for every correct answer 2 points and for every wrong answer -1 point");
			System.out.println("Rule 4:Follow the above rules ");
	    ArrayList<Question> quiz = new ArrayList<>();
        quiz.add(new Question("What is the capital of India?",
                new String[]{"Bengal", "New Delhi", "kashmir", "kerala", "Quit", "Lifeline"}, 2));
        quiz.add(new Question("Who is the cheif minister of Ap?",
                new String[]{"Modi", "Jagan", "Kcr", "chandrababuNaidu", "Quit", "Lifeline"}, 4));
        quiz.add(new Question("What is the largest ocean on Earth?",
                new String[]{"Pacific Ocean", "Indian Ocean", "Arctic Ocean", "Atlantic Ocean", "Quit", "Lifeline"}, 1));
        quiz.add(new Question("Who is the Pan Indaia Star ?",
                new String[]{"Prabhas", " Ram Charan", "Allu Arjun", " Ntr", "Quit", "Lifeline"}, 3));
        int score = 0;
        boolean lifelineUsed = false;
        Random random = new Random();
        try {
        for (int i = 0; i < quiz.size(); i++) {
            Question currentQuestion = quiz.get(i);
            boolean[] showOptions = {true, true, true, true, true, true}; 
            System.out.println("\nQuestion " + (i + 1) + ":");
            currentQuestion.displayQuestion(showOptions);

             System.out.print("Enter your choice (1-6): ");
            int userChoice = scanner.nextInt();
            if (userChoice == 5) {
                System.out.println("You chose to quit. Thank you for playing!");
                break;
            }
            if (userChoice == 6) {
                if (lifelineUsed) {
                    System.out.println("Lifeline already used! Choose a valid option.");
                    i--;
                    continue;
                }

                System.out.println("Lifeline Options:");
                System.out.println("1. Swap the question");
                System.out.println("2. Audience poll(percentage get 30)");
                System.out.println("3. 50-50");

                System.out.print("Choose a lifeline (1-3): ");
                int lifelineChoice = scanner.nextInt();

                switch (lifelineChoice) {
                    case 1: 
                        int randomIndex = random.nextInt(quiz.size());
                        System.out.println("Swapping the question...");
                        i = randomIndex - 1;                        
                        break;
                    case 2:
                        int[] poll = new int[4];
                        poll[currentQuestion.correctAnswer - 1] = random.nextInt(41) + 60; // 60-100% for correct
                        int remaining = 100 - poll[currentQuestion.correctAnswer - 1];
                        int allocated = 0;

                        for (int j = 0; j < poll.length; j++) {
                            if (j != currentQuestion.correctAnswer - 1) {
                                int allocation = j < 2 ? random.nextInt(Math.min(remaining - allocated, 30)) : remaining - allocated;
                                poll[j] = allocation;
                                allocated += allocation;
                            }
                        }

                        System.out.println("Audience poll results:");
                        for (int j = 0; j < poll.length; j++) {
                            System.out.println("Option " + (j + 1) + ": " + poll[j] + "%");
                        }
                        break;
                    case 3:
                       System.out.println("50-50 Lifeline Activated!");
                        boolean[] fiftyOptions = {false, false, false, false};
                        fiftyOptions[currentQuestion.correctAnswer - 1] = true;
                        int randomWrong;
                        do {
                            randomWrong = random.nextInt(4);
                        } while (randomWrong == currentQuestion.correctAnswer - 1);
                        fiftyOptions[randomWrong] = true;
                        currentQuestion.displayQuestion(fiftyOptions);
                        break;

                    default:
                        System.out.println("Invalid lifeline choice.");
                        i--;
                        break;
                
                
            }
                lifelineUsed = true;
                continue;
            }

            if (currentQuestion.isCorrect(userChoice)) {
                System.out.println("Correct!");
                score++;
                System.out.println("score is:"+score);

            } else {
                System.out.println("Wrong! The correct answer was " + currentQuestion.correctAnswer + ".");
                System.out.println("Game over. Your final score is: " + score);
                break;
            }
            if (i == quiz.size() - 1) {
                System.out.println("Congratulations! You completed the quiz.");
                System.out.println("Your total score is: " + score);
                System.out.println("Congratulations for being certified in the Quiz Game");
                System.out.println("Name :"+name);
        		System.out.println("ID IS:"+id);
        		System.out.println("EMAIL IS:"+email);
        		System.out.println("PLACE :"+place);
        		

                
            }
        }
		}
        catch(Exception e) {
        	System.out.println(e);
        }}
        else {
        	System.out.println("Terminate the game");
        }
	    System.out.println("THANK YOU");
    }
		
}

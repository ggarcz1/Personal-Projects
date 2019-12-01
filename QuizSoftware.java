//Gilbert Garczynski
//Original date: Febuary 12, 2018
//Updates: November 30, 2019
import java.util.*;
import java.io.*;
import java.text.*;


public class QuizSoftware {
 public static void main(String[] args) throws IOException {
  login("", "", "");
 }
//determine if login credentials are valid or invalid.  After three failed attempts, program terminates.
 public static void login(String userNa, String name1, String name2) throws IOException {
  int incorrect = 0, row = 0, col1 = 0, row1 = 0;
  String username = "null", password;
  boolean test = false;
  //count number of lines in users file
  File users_info = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\UsersInfo_006.txt");
  Scanner scan1 = new Scanner(users_info);
  while (scan1.hasNext()) {
   scan1.nextLine();
   row++;
  }
  scan1.close();
  File user_info2 = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\UsersInfo_006.txt");
  int rows = 0;
  //read users file into an array
  Scanner scan2 = new Scanner(user_info2);
  String[][] xx = new String[row][6];
  while (scan2.hasNext()) {
   String[] userArr = scan2.nextLine().split("\t");
   for (int column = 0; column < 6; column++)
    xx[rows][column] = userArr[column];
   rows++;
  }
  scan2.close();
  
//code to veriy username and password
  while (username != "Done") {
   int loop = 2;
   incorrect = 0;
   while (loop == 2) {
    test = false;
    //get user input, username and password
    Scanner fkeyboard = new Scanner(System.in);
    System.out.println("Enter your Username(Done to quit):");
    username = fkeyboard.nextLine();
    while (username.equalsIgnoreCase("Done")) {
     System.out.println("Program terminated.");
     System.exit(0);
    }
    System.out.println("Enter your Password:");
    password = fkeyboard.nextLine();
    int y = 0;
//check input against values in array
    for (row1 = 0; row1 < rows; row1++) {
      //if valid, enter loop here
     if ((username.equals(xx[row1][col1])) && (password.equals(xx[row1][col1 + 1]))) {
      if ("Instructor".equals(xx[row1][5])) {
       String userNamz = xx[row1][0], firstNamez = xx[row1][2], lastNamez = xx[row1][3];
       whoLogin(firstNamez, lastNamez, userNamz);
       Scanner scan3 = new Scanner(System.in);
       while (y == 0) {
        System.out.println("Enter 1 to add new student, 2 to display stats, 3 to add"
          + " new questions, or 4 to add an instructor(0 to quit): ");
        int teachNum = scan3.nextInt();
        if (teachNum == 0)
         System.exit(0);
        else if (teachNum == 1) {
         addUser();
         y = 0;
        } else if (teachNum == 2) {
         displayStats();
         y = 0;
        } else if (teachNum == 3) {
         addQuest();
         y = 0;
        } else if (teachNum == 4) {
         addInstr();
         y = 0;
        } else {
         y = 0;
         System.out.println("Error: Invalid input.");
        }
       }
       scan3.close();
       System.exit(0);
      }
      System.out.println("Access Granted");
      test = true;
      loop = 4;
      //valid credentials
      String userNam = xx[row1][0], firstName = xx[row1][2], lastName = xx[row1][3];
      //writes date, time, name of who logs in
      whoLogin(firstName, lastName, userNam);
      //select student option
      optionStudent(firstName, lastName, userNam);
     }
    }
    fkeyboard.close();
    //wrong login info
    if (test == false) {
     System.out.println("Login Credentails Incorrect");
     incorrect++;
    }
    //after 3 failed login attempts, program exits
    if (incorrect == 3) {
     System.out.println("Program terminated.");
     System.exit(0);
    }
   }
  }
 }

 //method to log who logs into the system and at what time and date
 public static String whoLogin(String firstName, String lastName, String userNam) throws IOException {
  FileWriter file = new FileWriter("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\LoginLog.txt", true);
  PrintWriter pw = new PrintWriter(file);
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM-dd-yy_hh:mm:ss");
  String dateAsString = simpleDateFormat.format(new Date());
  pw.println(firstName + "\t" + lastName + "\t" + userNam + "\t" + dateAsString);
  pw.close();
  return firstName;
 }
 
//upon sucessful student login, options are displayed through this method 
 public static String optionStudent(String firstName, String lastName, String userNam) throws IOException {
  Scanner in = new Scanner(System.in);
  boolean torf = true;
  //options for student
  while (torf) {
   System.out.println("Enter 1 to take quiz, 2 to view grades, 3 for instructor info"
     + " and 4 for student info(0 to quit): ");
   int z = in.nextInt();
   if (z == 1)
    questions(firstName, lastName, userNam);
   else if (z == 2)
    viewGrades();
   else if (z == 3)
    contactInfoInst();
   else if (z == 4)
    contactInfoStudent();
   else if (z == 0) {
    torf = false;
    System.exit(0);
   } else {
    torf = false;
    System.out.println("Invalid selection");
   }
  }
  in.close();
  return firstName;
 }
//questions and answers are read from files and questions are displayed randomly
 public static String questions(String name1, String name2, String userNam) throws IOException {
  long timeStart = System.currentTimeMillis();
  String s1 = Long.toString(timeStart);
  Random rand1 = new Random();
  int rowsQBank = 0;
  //count number of rows in file
  File fileTestBank = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\TestBank.txt");
  Scanner in = new Scanner(fileTestBank);
  while (in.hasNext()) {
   in.nextLine();
   rowsQBank++;
  }
  //add each row to string array 
  String[] quizArray = new String[rowsQBank];
  for (int e = 0; e < rowsQBank; e++) {
   File were = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\TestBank.txt");
   Scanner sct = new Scanner(were);
   while (sct.hasNext()) {
    quizArray[e] = sct.nextLine();
    e++;
   }
   sct.close();
  }
  in.close();
//count rows in answers file 
  String quizAns[] = new String[rowsQBank];
  for (int o = 0; o < rowsQBank; o++) {
   File gamma = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\Answers.txt");
   Scanner hulu = new Scanner(gamma);
   int num = 1;
   while (hulu.hasNext()) {
    quizAns[o] = hulu.nextLine();
    o++;
   }
   hulu.close();
   String realQuestAnsQuiz[] = new String[10];
   String userQuizAns[] = new String[10];
   System.out.println("Attepmt " + num + "/3 for " + name1 + " " + name2 + ".");
   ArrayList<Integer> nums = new ArrayList<Integer>();
   for (int xet = 0; xet < 10; xet++) {
    int x = rand1.nextInt(rowsQBank + 1) + 0;
    if (nums.contains(x))
     x = rand1.nextInt(rowsQBank + 1) + 0;
    realQuestAnsQuiz[xet] = quizAns[x];
    System.out.println((xet + 1) + ". " + quizArray[x]);
    Scanner sisi = new Scanner(System.in);
    userQuizAns[xet] = sisi.nextLine();
    boolean turf = false;
    //check for valid answer input then write answer input to a file
    while (turf == false) {
     if (userQuizAns[xet].equalsIgnoreCase("false") || userQuizAns[xet].equalsIgnoreCase("true")
       || userQuizAns[xet].equalsIgnoreCase("t") || userQuizAns[xet].equalsIgnoreCase("f")) {
      turf = true;
      continue;
     } else {
      System.out.println("ERROR: Enter valid answer(ignore case, T, F, True, or False):");
      userQuizAns[xet] = sisi.nextLine();
     }
    }
    sisi.close();
   }
   int correct = 0;
   for (int g = 0; g < userQuizAns.length; g++)
    if (userQuizAns[g].toUpperCase().charAt(0) == realQuestAnsQuiz[g].charAt(0))
     correct++;
   String printArray[][] = new String[16][2];
   printArray[0][0] = name1;
   printArray[0][1] = name2;
   printArray[14][0] = userNam;
   printArray[15][0] = s1;
   printArray[2][0] = "User Answer\t";
   printArray[2][1] = "\tCorrect Answer";
   printArray[14][1] = " ";
   for (int t = 0; t < 10; t++)
    printArray[3 + t][0] = userQuizAns[t];
   for (int t = 0; t < 10; t++)
    printArray[3 + t][1] = realQuestAnsQuiz[t];
   printArray[13][0] = "Score: ";
   printArray[13][1] = (correct + "");
   print(printArray);
  }
  return name1;
 }

 public static void viewGrades() throws IOException {
  Scanner bravo = new Scanner(System.in);
  System.out.println("viewGrades()");
  // pass first name and last name here
  // then read Stats.txt for that and print line with first name and last name

  bravo.close();
 }
//print contact info of instructor 
 public static void contactInfoInst() throws IOException {
  System.out.println();
  File file = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\FirstLastEmail.txt");
  Scanner bravo = new Scanner(file);
  while (bravo.hasNext()) {
   String str = bravo.nextLine();
   if (str.contains("Instructor"))
    System.out.println(str + "\n");
  }
  bravo.close();
 }

 //print contact info of a specific student
 public static void contactInfoStudent() throws IOException {
  Scanner si = new Scanner(System.in);
  System.out.println("Enter the student's first name: ");
  String first = si.nextLine();
  System.out.println("Enter the student's last name: ");
  String last = si.nextLine();
  System.out.println();
  File file = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\FirstLastEmail.txt");
  Scanner bravo = new Scanner(file);
  int x = 0;
  while (bravo.hasNext()) {
   String str = bravo.nextLine();
   if (str.contains("Student") && str.contains(first) && str.contains(last)) {
    System.out.println(str + "\n");
    x = 4;
   }
  }
  if (x == 0)
   System.out.println("Student not found.\n");
  bravo.close();
  si.close();
 }

 //print results of a student's quiz to output and to a file
 public static void print(String array2d[][]) throws IOException {
  FileWriter fw = new FileWriter("Stats.txt", true);
  PrintWriter pw = new PrintWriter(fw);
  System.out.println(array2d[0][0] + " " + array2d[0][1]);
  SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMddyy_hhmmss");
  String dateAsString = simpleDateFormat.format(new Date());
  String s2 = array2d[15][0];
  Long timeStart = Long.parseLong(s2);
  long timeEnd = System.currentTimeMillis();
  long timeDiff = timeEnd - timeStart;
  double seconds = timeDiff / 1000;
  String arr[] = new String[4];
  arr[0] = array2d[0][0];
  arr[1] = array2d[0][1];
  arr[2] = array2d[13][1] + "0%";
  arr[3] = seconds + " seconds";
  for (int i = 0; i < 4; i++)
   pw.print(arr[i] + "\t");
  pw.println();
  pw.println(array2d[0][0] + "\t" + array2d[0][1] + "\t\t\t" + array2d[13][1] + "0%\t" + seconds + " seconds");
  pw.close();
  array2d[13][1] = array2d[13][1] + "0%";
  double minutes = 0;
  if ((seconds / 60) > 1) {
   minutes = (seconds / 60);
   seconds = (minutes % 60);
  }
  if (minutes < 1)
   System.out.printf("Elapsed Time: %.0f seconds\n", seconds);
  else
   System.out.printf("Elapsed Time: %.0f minutes %.0f seconds\n", minutes, seconds);
  array2d[1][0] = "Elapsed time: ";
  if (minutes < 1)
   array2d[1][1] = (seconds + " seconds");
  else
   array2d[1][1] = (minutes + " minutes, " + seconds + " seconds");
  System.out.print(array2d[2][0] + array2d[2][1]);
  for (int i = 3; i < 14; i++) {
   System.out.println("");
   for (int y = 0; y < 2; y++)
    System.out.print(array2d[i][y] + "\t\t\t\t\t\t");
  }
  System.out.println("");
  PrintWriter write = new PrintWriter(array2d[14][0] + "_COSC_236_Quiz_" + dateAsString + ".txt");
  for (int x = 0; x < 14; x++)
   write.println(array2d[x][0] + "\t" + array2d[x][1]);
  write.close();
 }

 //add a user, instructor use only
 public static void addUser() throws IOException {
  Scanner were = new Scanner(System.in);
  FileWriter tango = new FileWriter("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\UsersInfo_006.txt", true);
  PrintWriter pw = new PrintWriter(tango);
  Random rand = new Random();
  String newFirst, newLast, newUsername, newEmail, newPassword;
  System.out.println("Enter student’s first name: ");
  newFirst = were.nextLine();
  System.out.println("Enter student’s last name: ");
  newLast = were.nextLine();
  String alpha = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  String letter[] = new String[6];
  for (int i = 0; i < 6; i++) {
   int x = rand.nextInt(alpha.length()) + 1;
   char c = alpha.charAt(x);
   String str = Character.toString(c);
   letter[i] = str;
  }
  newPassword = letter[0] + letter[1] + letter[2] + letter[3] + letter[4] + letter[5];
  System.out.println("Enter student’s username: ");
  newUsername = were.nextLine();
  System.out.println("Enter student’s email: ");
  newEmail = were.nextLine();
  pw.println(newUsername + "\t" + newPassword + "\t" + newFirst + "\t" + newLast + "\t" + newEmail + "\tStudent");
  pw.close();
  were.close();
 }

 //see stats for the class, instructor use only
 public static void displayStats() throws IOException {
  Scanner bravo = new Scanner(System.in);
  System.out.println("Enter 1 to see the gradebook or 2 to see Stats: ");
  int input = bravo.nextInt();
  if (input == 1) {
   File file1 = new File("Stats.txt");
   Scanner sisi = new Scanner(file1);
   while (sisi.hasNext()) {
    String str = sisi.nextLine();
    System.out.println(str);
   }
   sisi.close();
  } else if (input == 2) {
   File file = new File("Stats.txt");
   Scanner tango = new Scanner(file);
   int row = 0;
   while (tango.hasNext()) {
    tango.nextLine();
    row++;
   }
   tango.close();
   int rows = 0;
   File asdfg = new File("Stats.txt");
   Scanner qwer = new Scanner(asdfg);
   String[][] tu = new String[row][4];
   while (qwer.hasNext()) {
    String[] userArr = qwer.nextLine().split("\t");
    for (int column = 0; column < 4; column++)
     tu[rows][column] = userArr[column];
    rows++;
   }
   qwer.close();
   double stats[][] = new double[row][2];
   int maxUser = 0;
   int minUser = 0;
   for (int i = 0; i < stats.length; i++)
    stats[i][0] = Double.parseDouble(tu[i][2]);
   for (int i = 0; i < stats.length; i++)
    stats[i][1] = Double.parseDouble(tu[i][3]);
   double total = 0, count = 0;
   double maxGrade = stats[0][0];
   for (int z = 0; z < stats.length; z++) {
    if (stats[z][0] > maxGrade) {
     maxGrade = stats[z][0];
     maxUser = z;
    }
    total += stats[z][0];
    count++;
   }

   double minGrade = stats[0][0];
   for (int c = 0; c < stats.length; c++)
    if (stats[c][0] < minGrade) {
     minGrade = stats[c][0];
     minUser = c;
    }
   double avg = total / count;
   double minTime = stats[0][1];
   int minTimeUser = 0;
   for (int v = 0; v < stats.length; v++)
    if (stats[v][1] < minTime) {
     minTime = stats[v][1];
     minTimeUser = v;
    }
   double avg2 = avg * 10;
   System.out.printf("The average grade is: %.2f", avg2);
   System.out.println(
     "%.\nThe highest grade is: " + tu[maxUser][0] + " " + tu[maxUser][1] + " " + (maxGrade * 10) + "%");
   System.out.println(
     "The lowest grade is: " + tu[minUser][0] + " " + tu[minUser][1] + " " + (minGrade * 10) + "%");
   System.out.println("The fastest quiz was completed in " + minTime + " seconds " + "by " + tu[minTimeUser][0]
     + " " + tu[minTimeUser][1]);
   Scanner si = new Scanner(System.in);
   System.out.println("Press enter to exit.");
   String exit = si.nextLine();
   System.out.println(exit);
   si.close();
  } else
   System.out.println("Enter valid selection.");
  bravo.close();
 }

 //add a question, instructor use only
 public static void addQuest() throws IOException {
  Scanner key = new Scanner(System.in);
  System.out.println("Would you like to write a question(A) or read one(B)?");
  String qorr = key.nextLine();
  if (qorr.equalsIgnoreCase("A")) {
   FileWriter tango = new FileWriter("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\TestBank.txt", true);
   PrintWriter pw = new PrintWriter(tango);
   System.out.println("Enter a true or false question: ");
   String quest = key.nextLine();
   pw.println(" ");
   pw.print(quest);
   pw.close();
   FileWriter uyo = new FileWriter("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\Answers.txt", true);
   PrintWriter sas = new PrintWriter(uyo);
   int yy = 0;
   while (yy == 0) {
    System.out.println("Enter answer (TRUE or FALSE): ");
    String ansQ = key.nextLine();
    if (ansQ.equalsIgnoreCase("TRUE") || ansQ.equalsIgnoreCase("FALSE")) {
     yy = 1;
     String ansQU = ansQ.toUpperCase();
     sas.println(" ");
     sas.print(ansQU);
    } else {
     yy = 0;
     System.out.println("Error.");
    }
    sas.close();
   }
  } else if (qorr.equalsIgnoreCase("B")) {
   System.out.println("Enter the file you wish to read from (with .txt): ");
   String filePath = key.nextLine();
   File file = new File("C:\\Users\\Gil Garcz\\Desktop\\Java\\" + filePath + "\\");
   Scanner solo = new Scanner(file);
   while (solo.hasNext()) {
    String fileQ = solo.nextLine();
    FileWriter fga = new FileWriter("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\TestBank.txt",
      true);
    PrintWriter sbs = new PrintWriter(fga);
    sbs.println(" ");
    sbs.print(fileQ);
    sbs.close();
   }
   solo.close();
   FileWriter uyo = new FileWriter("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\Answers.txt", true);
   PrintWriter sas = new PrintWriter(uyo);
   int chi = 0;
   while (chi == 0) {
    System.out.println("Enter answer (TRUE or FALSE): ");
    String ansQ = key.nextLine();
    if (ansQ.equalsIgnoreCase("TRUE") || ansQ.equalsIgnoreCase("FALSE")) {
     chi = 1;
     String ansQU = ansQ.toUpperCase();
     sas.println(" ");
     sas.print(ansQU);
    } else
     System.out.println("Error.");
   }
   sas.close();
  }
  key.close();
 }

 
 //add an instructor, instructor use only
 public static void addInstr() throws IOException {
  Scanner bravo = new Scanner(System.in);
  Random rand = new Random();
  FileWriter fw = new FileWriter("C:\\Users\\Gil Garcz\\Desktop\\Java\\FinalProject\\UsersInfo_006.txt", true);
  PrintWriter pw = new PrintWriter(fw);
  String ipass[] = new String[6];
  String firstName, lastName, password, userName, email;
  System.out.println("Enter instructor first name:");
  firstName = bravo.nextLine();
  System.out.println("Enter instructor last name:");
  lastName = bravo.nextLine();
  String pass = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghij" + "klmnopqrstuvwxyz123456789!@#$%^&*()_+";
  for (int i = 0; i < 6; i++) {
   int z = rand.nextInt(pass.length()) + 1;
   char c = pass.charAt(z);
   String str = Character.toString(c);
   ipass[i] = str;
  }
  password = ipass[0] + ipass[1] + ipass[2] + ipass[3] + ipass[4] + ipass[5];
  System.out.println("Enter instructor username:");
  userName = bravo.nextLine();
  System.out.println("Enter instructor email:");
  email = bravo.nextLine();
  pw.println(userName + "\t" + password + "\t" + firstName + "\t" + lastName + "\t" + email + "\tInstructor");
  pw.close();
  bravo.close();
 }
}
package com.example.dirtyCode;
public class DirtyExample {
    
  // Long method with deeply nested if-else and repeated code
  public void process(int userId, String action) {
      if (userId == 1) {
          if ("LOGIN".equals(action)) {
              System.out.println("User logged in");
              // Duplicated block of code
              System.out.println("Checking permissions...");
              System.out.println("Permissions granted for user " + userId);
              // Hardcoded value
              String userRole = "ADMIN";
              System.out.println("User role: " + userRole);
          } else if ("LOGOUT".equals(action)) {
              System.out.println("User logged out");
          } else if ("REGISTER".equals(action)) {
              System.out.println("User registered");
              // Duplicated block of code
              System.out.println("Checking permissions...");
              System.out.println("Permissions granted for user " + userId);
          } else {
              System.out.println("Unknown action");
          }
      } else if (userId == 2) {
          System.out.println("Different user actions not implemented");
      } else {
          System.out.println("Unknown user");
      }
  }

  // Example of a function with excessive parameters and meaningless names
  public int calc(int a, int b, int c, int d, int e) {
      return a + b + c + d + e;
  }

  // Unused method that SonarQube should flag as dead code
  public void unusedMethod() {
      System.out.println("This method is never called");
  }

  // Commented-out code (should be avoided)
  // public void commentedOutCode() {
  //    System.out.println("This is commented-out code and should be cleaned up");
  // }

  // Function that throws generic Exception and doesn't handle it properly
  public void riskyOperation() throws Exception {
      throw new Exception("Something went wrong");
  }
  
  // Poorly named variable and hardcoded value
  public void calculateAge() {
      int x = 2024; // Magic number (hardcoded year)
      int birthYear = 1990; // Hardcoded value
      System.out.println("Age: " + (x - birthYear));
  }
  
  // Repeated code logic
  public void repeatLogic() {
      System.out.println("Logic to do X");
      System.out.println("Logic to do X");
      System.out.println("Logic to do X");
  }
}

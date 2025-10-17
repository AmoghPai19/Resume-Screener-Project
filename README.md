# Resume Screener Project

## Project Overview
The Resume Screener is a web-based application built with **Java, Spring Boot, MySQL, HTML, CSS, and JavaScript** that automates the initial screening of resumes against job descriptions. It evaluates resumes based on keyword matching and provides a **score and matched skills**, along with maintaining a **history of analyzed resumes**.  

---

## Features
- Upload resumes in **text format**.
- Enter the **job description** for analysis.
- **Automated scoring** of resumes based on keyword matches.
- Maintains a **history of all analyzed resumes**.
- Displays **matched keywords and score** for each resume.
- Simple and responsive **frontend interface** using HTML, CSS, and JS.

---

## Technologies Used
- **Backend:** Java, Spring Boot, Spring Data JPA
- **Database:** MySQL
- **Frontend:** HTML, CSS, JavaScript
- **Build Tool:** Maven
- **Server:** Embedded Tomcat (Spring Boot)
- **Version Control:** Git

---

## Architecture / Flow of Execution

1. **Frontend**
   - User enters job description and uploads a resume.
   - Form is submitted to the backend using a POST request.

2. **Backend (Spring Boot)**
   - `ResumeController` receives the request.
   - `ResumeService` reads the resume file and analyzes it against the job description.
   - Keywords are matched while ignoring stop words.
   - Score is calculated based on **frequency** and **importance** of each keyword.
   - Results are saved in MySQL using JPA (`ScreeningResultRepository`).

3. **Database**
   - Stores resume text, job description, score, matched keywords, and timestamp.

4. **Frontend**
   - Displays the **score and matched keywords**.
   - Loads history of all analyzed resumes.

---

## Enhancements Made
- Removed common stop words like *we, are, a, the* to improve accuracy.
- Added **weighted scoring**: more important keywords contribute more to the total score.
- Stored **full history** of analyzed resumes with timestamp for future reference.

---

## Future Scope / AI Integration Ideas
- **Semantic matching:** Use NLP models to understand skills even if phrased differently (e.g., "Spring Boot" vs "Spring Framework").
- **Resume parsing:** Automatically extract sections like Education, Skills, Work Experience.
- **Custom AI scoring model:** Build a ML model to predict candidate suitability.
- **File format support:** Add PDF, DOCX parsing.
- **Dashboard:** Show analytics such as most common skills, average scores, etc.
- **Integration with job p**

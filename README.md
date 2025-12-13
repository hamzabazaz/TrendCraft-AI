TrendCraft AI – README

1. Overview
TrendCraft AI is a mini full-stack application that fetches real-time news articles, allows the user to select up to three articles, and generates a social-media-ready post including:
•	Short hooks
•	A caption
•	An AI-generated image prompt
The system integrates:
•	RSS News parsing
•	A modern Angular frontend
•	A Spring Boot backend
•	A local MySQL database
•	LLM generation using Groq API (OpenAI-compatible)
This project demonstrates practical AI-powered content automation with clean architecture and a modern UI.
________________________________________
2. Features
•	Fetch real news from Google News RSS
•	View articles in clean, responsive card layout
•	Select a maximum of three articles
•	Generate hooks, caption, and image prompt using LLM
•	Save generated results in MySQL
•	Professional, modern frontend design
________________________________________

3. Project Structure
TrendCraftAI/
│
├── backend/
│   └── news2Post/
│       └── (Spring Boot application)
│
└── frontend/
    └── news2post-ui/
        └── (Angular application)
________________________________________

4. Tech Stack
Frontend
•	Angular 17 (standalone components)
•	TypeScript, SCSS
•	Responsive UI layout
Backend
•	Spring Boot 3
•	Java 17
•	Spring Web, Spring JPA, Hibernate
•	Groq LLM 
•	Rome RSS Feed Parser
•	MySQL Database
________________________________________

5. Article Source
The application uses the Google News RSS feed:
https://news.google.com/rss/search?q=YOUR_TOPIC
This provides a mixture of news articles from multiple publishers.
If Google RSS is unavailable, the backend can be extended with:
•	NewsAPI.org
•	Custom mock articles
•	Other RSS sources
________________________________________

6. Setup Instructions
6.1 Clone the repository
git clone https://github.com/yourusername/TrendCraft-AI.git
cd TrendCraft-AI
________________________________________

7. Backend Setup (Spring Boot)
7.1 Create MySQL database
CREATE DATABASE trendcraft;
7.2 Configure application.properties
spring.datasource.url=jdbc:mysql://localhost:3306/trendcraft
spring.datasource.username=YOUR_USER
spring.datasource.password=YOUR_PASSWORD
spring.jpa.hibernate.ddl-auto=update

app.openaire.apiKey=YOUR_GROQ_API_KEY
app.openaire.model=llama3-8b-8192
7.3 Run the backend
cd backend/news2Post/news2Post
mvn spring-boot:run
Backend runs at:
http://localhost:8080
________________________________________

8. Frontend Setup (Angular)
8.1 Install dependencies
cd frontend/news2post-ui
npm install
8.2 Run the frontend
ng serve
Frontend runs at:
http://localhost:4200
________________________________________
9. Environment Variables
These must NOT be committed to Git:
Variable	Description
app.openaire.apiKey	          Groq API key for LLM
app.openaire.model	         Model name (e.g., llama3-8b-8192)
(The repository excludes .properties with real keys.)
________________________________________

10. Architecture Overview
Frontend
•	Angular standalone components
•	Fetches articles from backend
•	Displays article cards with selection states
•	Sends selected articles to backend to generate a post
•	Displays generated hooks, caption, and image prompt
Backend
•	Fetches and parses Google News RSS
•	Safely cleans and stores article text
•	Limits selection to maximum 3 articles
•	Sends structured prompt to Groq LLM
•	Stores generated output in MySQL
•	Returns structured JSON to frontend
Data Flow
Frontend → Backend (fetch articles)
Frontend → Backend (generate post)
Backend → LLM API → Backend → Frontend
Frontend → Display final output
________________________________________

11. Limitations
•	No authentication implemented
•	Does not generate final social media image—only prompt
________________________________________

12. Future Improvements
•	Add authentication and user profiles
•	Save multiple generated posts per user
•	Generate real images using DALL-E style models
•	Add trending-topic suggestions
•	Add pagination and filtering for articles
•	Dockerize backend and frontend for easier deployment
________________________________________

13. Conclusion
TrendCraft AI demonstrates practical, modern full-stack engineering with:
•	Real-time news sourcing
•	AI-powered content generation
•	Clean Spring Boot backend
•	Stylish Angular UI
•	Production-ready architecture
This project serves as an excellent showcase of integrating AI with full-stack development


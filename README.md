

\# VoiceScribe



VoiceScribe is a voice transcription application designed to help users

record speech, convert it into text, edit transcripts, and manage saved

transcriptions.



\## Features



\- Voice recording and transcription interface

\- Editable transcript text area

\- Transcript history and management

\- React and Vite frontend

\- Spring Boot REST API backend

\- MySQL database integration

\- ElevenLabs integration for realtime transcription



\## Technology Stack



\*\*Frontend\*\*

\- React

\- Vite

\- JavaScript

\- CSS

\- Lucide React



\*\*Backend\*\*

\- Java

\- Spring Boot

\- Spring Data JPA

\- MySQL

\- Maven



\*\*Speech Services\*\*

\- ElevenLabs API



\## Project Structure



```text

voicescribe/

├── backend/

├── frontend/

├── .gitignore

└── README.md

```



\## Prerequisites



\- Node.js and npm

\- Java 21 or the version configured by the backend project

\- MySQL

\- Maven (or the included Maven wrapper)

\- An ElevenLabs API key for speech services



\## Run the Frontend



```bash

cd frontend

npm install

npm run dev

```



\## Run the Backend



Configure the MySQL database and environment variables in the backend

configuration before starting the application.



From the backend directory, run:



```bash

./mvnw spring-boot:run

```



On Windows PowerShell:



```powershell

.\\mvnw.cmd spring-boot:run

```



\## Configuration



Do not commit API keys, passwords, or private environment files.



Configure the ElevenLabs API key and database credentials using

environment variables or a local configuration file excluded from Git.



\## Project Status



Frontend and backend development completed for the current version.

Additional deployment and production configuration may be required

before hosting the application publicly.



\## Author



Developed as a full-stack application project.




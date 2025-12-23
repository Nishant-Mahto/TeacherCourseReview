# Teacher & Course Review Application 📱📈
### *A High-Performance Native Android Solution with Integrated Sentiment Analysis*

## **Project Overview**
[cite_start]This application was developed to modernize inefficient, legacy feedback collection methods in academic environments [cite: 141-144, 149]. [cite_start]By utilizing a **native Android stack**, the system provides a faster, more accessible, and more engaging platform for students to review faculty and course curriculum [cite: 147-152, 680].



---

## **Technical Stack & Architecture**
* [cite_start]**Language**: **Kotlin** (optimized for null-safety and conciseness) [cite: 196, 219-223].
* [cite_start]**IDE**: **Android Studio** [cite: 215-217].
* [cite_start]**Backend**: **Google Firebase** (Backend-as-a-Service model) [cite: 197-202].
* [cite_start]**Database**: **Firebase Realtime Database** (NoSQL JSON tree structure) [cite: 204-207, 230-234].
* [cite_start]**Intelligence**: **Hybrid Sentiment Analysis Engine** that correlates quantitative ratings with qualitative keyword scanning [cite: 166, 403-411].



---

## **Security & Engineering Integrity**
[cite_start]This project follows **DevSecOps best practices** to ensure data integrity and credential safety [cite: 683-684]:
* [cite_start]**History Purge**: Sensitive configuration files were permanently purged from the version control history using `git filter-branch` to ensure zero-leakage of API credentials .
* [cite_start]**Identity Management**: **Firebase Authentication** ensures only verified students can submit reviews [cite: 162, 224-228].
* [cite_start]**Access Control**: **Database Security Rules** (`auth != null`) prevent unauthorized read/write access to the JSON tree [cite: 257-258].
* [cite_start]**Endpoint Security**: API keys are restricted to the application's unique **SHA-1 fingerprint** and package name in the Google Cloud Console [cite: 543-544].



---

## **Key Features**
* [cite_start]**Dynamic UI**: Category selection (Teachers/Courses) dynamically updates subject spinners to reduce input friction [cite: 164, 280-282, 618].
* [cite_start]**Real-Time Sync**: Sub-second data persistence and synchronization across the cloud [cite: 210-211, 635].
* [cite_start]**Smart Analytics**: On-device processing tags reviews as **POSITIVE**, **NEGATIVE**, or **NEUTRAL** instantly upon submission [cite: 152, 291, 399-402].



---

## **Installation & Setup (Prerequisites)**
[cite_start]For security reasons, the `google-services.json` configuration has been removed from this repository . To run this project locally:
1.  **Clone the Repo**: `git clone https://github.com/Nishant-Mahto/TeacherCourseReview.git`
2.  **Firebase Setup**: Create a new project in the [Firebase Console](https://console.firebase.google.com/).
3.  **Config File**: Register an Android app with the package name `com.example.teachercoursereview` and download your `google-services.json`.
4.  **Place Config**: Move the file into the `/app` directory of the project.
5.  **Build**: Open in Android Studio and wait for Gradle sync.
6.  **Run**: Click the **Green Play Button (►)**.

---

## **Testing & Validation**
* [cite_start]**Beta Testing**: Successfully tested with a focus group, achieving a **100% data sync success rate** [cite: 660-661].
* [cite_start]**Performance**: Verified sub-second latency for all write operations [cite: 668-669].
* [cite_start]**Stability**: Handled regional connectivity challenges by reconfiguring SDK endpoints for the `asia-southeast1` region [cite: 550-554].

---

**Developed by Nishant Mahto** *Manipal University Jaipur | BCA 2023-2026* *NPTEL Certified in Computer Vision*

# Teacher & Course Review System

This is a native Android application built to fix the slow and manual feedback processes in universities. Instead of paper forms, students get a fast, mobile-first way to rate faculty and courses.

## Why this stack?
* **Kotlin**: Used for its modern syntax and null-safety to prevent runtime crashes.
* **Firebase (BaaS)**: I chose a Backend-as-a-Service model to handle scaling and authentication without the overhead of managing a custom server.
* **NoSQL Architecture**: Realtime Database was used because sub-second data syncing was a priority for the user experience.

## The "Smart" Part: Hybrid Sentiment Analysis
Instead of just storing raw text, I built a client-side engine that "thinks" before it writes. It correlates the quantitative star rating with a qualitative keyword scan (matching positive/negative patterns) to tag the tone of a review the moment it’s submitted.



## Engineering Challenges & Fixes
* **Regional Connectivity**: Discovered a "silent write" bug where data wasn't hitting the cloud. I traced this to a regional mismatch and hardcoded the `asia-southeast1` endpoint to fix it.
* **Serialization Bug**: Fixed a NoSQL mapping crash by adding manual no-argument constructors to my Kotlin data classes.
* **Security**: Scrubbed the Git history to remove sensitive configs. The backend is now protected via Firebase Security Rules (`auth != null`) and SHA-1 API restrictions.



## How to use it
1. Clone this repo.
2. Go to the Firebase Console and create a project.
3. Download your `google-services.json` (Package: `com.example.teachercoursereview`).
4. Drop it into the `/app` folder.
5. Hit **Run** in Android Studio.

## Performance
* **Sync Speed**: Verified sub-second persistence during beta testing.
* **UI**: Used dynamic spinners to make sure students only see relevant subjects based on their category choice.

---
**Nishant Mahto**
*BCA 2023-2026 | Manipal University Jaipur*
*NPTEL Certified in Computer Vision*

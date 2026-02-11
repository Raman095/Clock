# ⏰ Clock App – Digital & Analog (Jetpack Compose)

## 📌 Introduction

This is a modern Android Clock Application built using **Jetpack Compose** and **Material 3**.

The app displays the current system time in two formats:

- 🕒 Custom Analog Clock  
- ⏱️ Real-time Digital Clock  

The analog clock is fully custom-built using the **Canvas composable provided by Jetpack Compose**, demonstrating custom drawing, coordinate calculations, and real-time UI updates in a declarative framework.

System time is fetched using the `Calendar` class from the **Java Standard Library**, ensuring accurate and timezone-aware updates.

---

## 🚀 Features

- 🕒 Custom Analog Clock built using Compose `Canvas`
- ⏱️ Live Digital Clock display
- 🔄 Real-time second-by-second updates
- 🎨 Clean UI using Material 3
- 📱 Fully built using Jetpack Compose

---

## 📸 Screenshots

<p align="center">
  <img src="images/WhatsApp Image 2026-02-11 at 1.10.13 PM.jpeg" width="300"/>
  <img src="images/WhatsApp Video 2026-02-11 at 1.10.13 PM.gif" width="300"/>
</p>

---

## 🧠 How Time Is Fetched

The app retrieves the current system time using:

```kotlin
Calendar.getInstance()
```

- `Calendar` is a built-in class from `java.util`
- `getInstance()` returns a `Calendar` object containing the current date and time
- Hour, minute, and second values are extracted using:

```kotlin
calendar.get(Calendar.HOUR)
calendar.get(Calendar.MINUTE)
calendar.get(Calendar.SECOND)
```

These values are used to update both the digital and analog clocks dynamically.

---

## 🎨 Analog Clock Implementation (Jetpack Compose Canvas)

The analog clock UI is custom-designed using the **`Canvas` composable from Jetpack Compose** (`androidx.compose.foundation.Canvas`).

Using Canvas, the following components are drawn:

- Clock outer circle using `drawCircle()`
- Hour, minute, and second hands using `drawLine()`
- Hour and minute tick marks
- Angle calculations based on time values
- Dynamic recomposition for smooth real-time updates

This implementation demonstrates understanding of:

- Compose drawing APIs
- Coordinate systems and offsets
- Angle-based calculations
- Declarative UI updates in Compose
- State-driven recomposition

---

## 🏗️ Tech Stack

- **Kotlin**
- **Jetpack Compose**
- **Material 3**
- **Compose Canvas (Custom Drawing)**
- **java.util.Calendar (System Time API)**

---

## 🔮 Planned Future Enhancements

The following features may be added in future updates:

- ⏲️ Stopwatch functionality
- ⏰ Alarm system
- ⌛ Countdown Timer
- 🌙 Dark mode improvements

---

## 🎯 Project Purpose

This project was built to:

- Practice custom UI drawing using the Compose `Canvas` composable
- Implement real-time system time updates
- Strengthen understanding of state and recomposition in Jetpack Compose
- Build a functional and extensible clock application

---

## 👨‍💻 Author

Developed as part of Android development practice using modern Android UI principles and system APIs.

# E-Commerce Website Testing Project

## Introduction

This repository contains the automation testing project for an open-source e-commerce website using Katalon Studio. The project was developed for educational and software quality assurance purposes, covering both manual testing and automated testing activities.

The testing scope includes customer-side and admin-side functionalities such as authentication, product management, shopping cart operations, checkout process, and UI validation.

---

# Project Objectives

The purpose of this project is to:

- Perform functional testing on an e-commerce website
- Design and execute test cases
- Automate test scenarios using Katalon Studio
- Practice regression testing
- Perform bug reporting and defect tracking
- Improve software quality assurance skills

---

# Testing Scope

The project covers testing for the following modules:

## Customer-Side Testing

- User Registration
- User Login / Logout
- Product Search
- Product Filtering
- Product Details
- Add to Cart
- Update Cart
- Checkout Process
- User Profile

## Admin-Side Testing

- Admin Login
- Product Management
- Category Management
- Order Management
- Customer Management
- Dashboard Navigation

---

# Testing Types

The following testing types are included in this project:

- Functional Testing
- UI Testing
- Smoke Testing
- Regression Testing
- End-to-End Testing
- Automation Testing

---

# Tools & Technologies

- Katalon Studio
- Groovy
- Selenium WebDriver
- Git & GitHub
- Google Chrome

---

# Project Structure

```text
Ecommerce_Testing/
│
├── Test Cases/
├── Test Suites/
├── Object Repository/
├── Profiles/
├── Keywords/
├── Data Files/
├── Reports/
└── Include/
```

---

# Requirements

Before running the project, make sure the following software is installed:

- Katalon Studio
- Google Chrome
- ChromeDriver compatible with Chrome version
- Git (optional)
- WampServer
- PHP & MySQL

---

# Source Code Repository

The source code repository of the e-commerce website is maintained separately:

```text
ecommerce-website-sourcecode
```

The source code project must be running locally before executing automation test cases.

---

# Environment Setup

## 1. Clone Repository

```bash
git clone https://github.com/your-username/ecommerce-website-testing.git
```

---

## 2. Open Project in Katalon

Open Katalon Studio:

```text
File → Open Project
```

Select the cloned project folder.

---

## 3. Run Website Locally

Make sure the source code project is running:

```text
http://localhost/E-commerce
```

Admin panel:

```text
http://localhost/E-commerce/admin
```

---

# Running Test Cases

## Run Individual Test Case

```text
Test Cases → Right Click → Run
```

## Run Test Suite

```text
Test Suites → Right Click → Run
```

---

# Team Collaboration Workflow

To avoid conflicts during team development:

- Each member should create a separate branch
- Pull latest changes before starting work
- Commit changes regularly
- Push completed work to GitHub

Example branches:

```text
feature/login-test
feature/cart-test
feature/checkout-test
```

---

# Recommended Environment

All team members should use:

- Same Katalon version
- Same Chrome version
- Same ChromeDriver version

to reduce compatibility issues.

---

# Git Ignore Recommendation

The following generated folders should not be committed:

```text
bin/
Libs/
Reports/
tmp/
.cache/
```

---

# Known Limitations

- Dynamic waits may need improvement
- Some UI elements may change depending on browser version
- Test data is partially hardcoded
- Payment functionality is not fully implemented

---

# Future Improvements

Planned future enhancements:

- Data-driven testing
- Cross-browser testing
- API testing
- CI/CD integration
- Parallel execution
- Advanced reporting

---

# Contributors

This project was developed as part of a software testing and quality assurance group project using Katalon Studio and GitHub collaboration workflow.

---

# License

This project is intended for educational and learning purposes only.

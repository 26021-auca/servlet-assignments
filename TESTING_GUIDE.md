# Testing Guide for Servlet Assignments

## 📋 Test Plan Overview

This document provides comprehensive test cases for both servlet assignments to ensure they work correctly.

---

## 🔐 Assignment 1: Login Servlet Testing

### Test Case 1.1: Valid Login (Strong Password)

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/login.html`
2. Enter Username: `testuser`
3. Enter Password: `Password123` (8+ characters)
4. Click "Login" button

**Expected Result:**
- ✅ Redirects to result page
- ✅ Displays: "Login Successful!"
- ✅ Shows: "Welcome **testuser**"
- ✅ Username is displayed correctly
- ✅ "Back to Login" link is present

---

### Test Case 1.2: Weak Password (Less than 8 characters)

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/login.html`
2. Enter Username: `john`
3. Enter Password: `pass123` (7 characters)
4. Click "Login" button

**Expected Result:**
- ✅ Redirects to result page
- ✅ Displays: "Weak Password!"
- ✅ Shows: "Hello **john**, your password is weak. Try a strong one."
- ✅ Username is displayed correctly
- ✅ "Back to Login" link is present

---

### Test Case 1.3: Minimum Valid Password (Exactly 8 characters)

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/login.html`
2. Enter Username: `alice`
3. Enter Password: `abcd1234` (exactly 8 characters)
4. Click "Login" button

**Expected Result:**
- ✅ Displays: "Login Successful!"
- ✅ Shows: "Welcome **alice**"
- ✅ Password is considered strong (≥8 characters)

---

### Test Case 1.4: Very Short Password

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/login.html`
2. Enter Username: `bob`
3. Enter Password: `1` (1 character)
4. Click "Login" button

**Expected Result:**
- ✅ Displays: "Weak Password!"
- ✅ Shows weak password message with username

---

### Test Case 1.5: Empty Username (HTML5 Validation)

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/login.html`
2. Leave Username field empty
3. Enter Password: `Password123`
4. Click "Login" button

**Expected Result:**
- ✅ Browser shows validation message: "Please fill out this field"
- ✅ Form is NOT submitted
- ✅ User remains on login page

---

### Test Case 1.6: Empty Password (HTML5 Validation)

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/login.html`
2. Enter Username: `testuser`
3. Leave Password field empty
4. Click "Login" button

**Expected Result:**
- ✅ Browser shows validation message: "Please fill out this field"
- ✅ Form is NOT submitted
- ✅ User remains on login page

---

### Test Case 1.7: Special Characters in Username

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/login.html`
2. Enter Username: `user<script>alert('xss')</script>`
3. Enter Password: `Password123`
4. Click "Login" button

**Expected Result:**
- ✅ Special characters are escaped (XSS protection)
- ✅ Username displays safely without executing scripts
- ✅ Shows: "Welcome **user&lt;script&gt;alert('xss')&lt;/script&gt;**"

---

### Test Case 1.8: Direct GET Request to Servlet

**Steps:**
1. Navigate directly to: `http://localhost:8080/servlet-assignments/LoginServlet`

**Expected Result:**
- ✅ Redirects back to: `login.html`
- ✅ No error is shown

---

## 🔀 Assignment 2: Redirect Servlet Testing

### Test Case 2.1: Valid Search Query

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter Search Query: `Java servlets tutorial`
3. Click "Fetch" button

**Expected Result:**
- ✅ Redirects to Google search page
- ✅ URL contains: `https://www.google.com/search?q=Java+servlets+tutorial`
- ✅ Google shows search results for "Java servlets tutorial"

---

### Test Case 2.2: Search Query with Special Characters

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter Search Query: `Java & C++ comparison`
3. Click "Fetch" button

**Expected Result:**
- ✅ Redirects to Google search
- ✅ Special characters are URL-encoded correctly
- ✅ URL contains: `https://www.google.com/search?q=Java+%26+C%2B%2B+comparison`
- ✅ Google interprets the query correctly

---

### Test Case 2.3: Single Word Query

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter Search Query: `Servlets`
3. Click "Fetch" button

**Expected Result:**
- ✅ Redirects to Google search
- ✅ URL contains: `https://www.google.com/search?q=Servlets`
- ✅ Google shows results for "Servlets"

---

### Test Case 2.4: Query with Numbers

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter Search Query: `Top 10 programming languages 2024`
3. Click "Fetch" button

**Expected Result:**
- ✅ Redirects to Google search
- ✅ Numbers are handled correctly
- ✅ Google shows relevant results

---

### Test Case 2.5: Query with Multiple Spaces

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter Search Query: `how    to    learn    java` (multiple spaces)
3. Click "Fetch" button

**Expected Result:**
- ✅ Redirects to Google search
- ✅ Multiple spaces are handled correctly
- ✅ Query is URL-encoded properly

---

### Test Case 2.6: Empty Search Query (HTML5 Validation)

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Leave the search field empty
3. Click "Fetch" button

**Expected Result:**
- ✅ Browser shows validation message: "Please fill out this field"
- ✅ Form is NOT submitted
- ✅ User remains on the page

---

### Test Case 2.7: Query with Only Spaces

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter Search Query: `     ` (only spaces)
3. Click "Fetch" button

**Expected Result:**
- ✅ Browser validation should catch this (required field)
- OR
- ✅ If submitted, redirects back to `redirect.html` (servlet handles empty trim)

---

### Test Case 2.8: Query with Emojis

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter Search Query: `😊 happy emoji search`
3. Click "Fetch" button

**Expected Result:**
- ✅ Redirects to Google search
- ✅ Emojis are URL-encoded correctly
- ✅ Google handles the query appropriately

---

### Test Case 2.9: Very Long Search Query

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/redirect.html`
2. Enter a very long search query (500+ characters)
3. Click "Fetch" button

**Expected Result:**
- ✅ Redirects to Google search
- ✅ Long query is URL-encoded
- ✅ No error occurs (though Google may truncate)

---

### Test Case 2.10: Direct GET Request to Servlet

**Steps:**
1. Navigate directly to: `http://localhost:8080/servlet-assignments/RedirectServlet`

**Expected Result:**
- ✅ Redirects back to: `redirect.html`
- ✅ No error is shown

---

## 🌐 General Testing

### Test Case 3.1: Index Page Navigation

**Steps:**
1. Navigate to: `http://localhost:8080/servlet-assignments/`

**Expected Result:**
- ✅ Index page loads successfully
- ✅ Shows both assignment cards
- ✅ "Try Login Servlet" link works
- ✅ "Try Redirect Servlet" link works

---

### Test Case 3.2: Browser Compatibility

**Test on multiple browsers:**
- ✅ Chrome
- ✅ Firefox
- ✅ Edge
- ✅ Safari

**Expected Result:**
- ✅ All pages render correctly
- ✅ Forms work in all browsers
- ✅ Styling is consistent
- ✅ Redirects work properly

---

### Test Case 3.3: Mobile Responsiveness

**Steps:**
1. Open DevTools (F12)
2. Toggle device toolbar
3. Test on different screen sizes

**Expected Result:**
- ✅ Pages are responsive
- ✅ Forms are usable on mobile
- ✅ Buttons are easily clickable
- ✅ Text is readable

---

## 📊 Test Summary Template

Use this template to track your testing:

```
Date: __________
Tester: __________

Assignment 1 - Login Servlet:
[ ] Test 1.1 - Valid Login
[ ] Test 1.2 - Weak Password
[ ] Test 1.3 - Minimum Password
[ ] Test 1.4 - Very Short Password
[ ] Test 1.5 - Empty Username
[ ] Test 1.6 - Empty Password
[ ] Test 1.7 - Special Characters
[ ] Test 1.8 - Direct GET Request

Assignment 2 - Redirect Servlet:
[ ] Test 2.1 - Valid Search
[ ] Test 2.2 - Special Characters
[ ] Test 2.3 - Single Word
[ ] Test 2.4 - Numbers
[ ] Test 2.5 - Multiple Spaces
[ ] Test 2.6 - Empty Query
[ ] Test 2.7 - Only Spaces
[ ] Test 2.8 - Emojis
[ ] Test 2.9 - Long Query
[ ] Test 2.10 - Direct GET Request

General Testing:
[ ] Test 3.1 - Index Navigation
[ ] Test 3.2 - Browser Compatibility
[ ] Test 3.3 - Mobile Responsiveness

Total Tests Passed: ___/27
```

---

## 🐛 Common Issues and Solutions

### Issue: Servlet returns 404

**Solution:**
- Check servlet is deployed correctly
- Verify URL mapping in @WebServlet annotation
- Ensure Tomcat is running
- Check `web.xml` configuration

### Issue: Form submission doesn't work

**Solution:**
- Check `action` attribute in form matches servlet path
- Verify `method="post"` is set correctly
- Check browser console for JavaScript errors

### Issue: Special characters not displayed correctly

**Solution:**
- Ensure `response.setContentType("text/html; charset=UTF-8")`
- Check XSS protection is working (escapeHtml method)

---

## ✅ Final Checklist

Before considering testing complete:

- [ ] All 27 test cases executed
- [ ] No critical bugs found
- [ ] Documentation reviewed
- [ ] Code follows best practices
- [ ] Security measures verified (XSS protection, input validation)
- [ ] Cross-browser testing completed
- [ ] Mobile responsiveness verified
- [ ] Performance is acceptable

---

**Happy Testing! 🎯**

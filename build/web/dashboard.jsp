<%-- 
    Document   : dashboard
    Created on : 08 Jul 2025, 17:58:03
    Author     : kgsmi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Styles/dashboardstyle.css">
        <title>Dashboard</title>
    </head>
    <body>
        <div class="dashboard">
            
            <div class="div1">
                <div class="header-content">
                    <h1>Welcome Student</h1>
                    <form action="index.jsp" method="POST">
                        <button class="logout-btn" type="submit">Logout</button>
                    </form>
                </div>
            </div>

            <div class="div2">
                <h2>Student Info</h2>
                <p><strong>Name:</strong> Student Name</p>
                <p><strong>Student Number:</strong> Student Number</p>
                <p><strong>Email:</strong> Student Email</p>
                <p><strong>Phone Number:</strong> Student Phone Number</p>
            </div>

            <div class="div3">
                <h3>Tip of the Day</h3>
                <p>Gonna add these in through a servlet</p>
            </div>

            <div class="div4">
                <h2>July 2025</h2>
                <table>
                    <tr>
                        <th>Sun</th><th>Mon</th><th>Tue</th><th>Wed</th>
                        <th>Thu</th><th>Fri</th><th>Sat</th>
                    </tr>
                    <tr>
                        <td></td><td></td><td>1</td><td>2</td><td>3</td><td>4</td><td>5</td>
                    </tr>
                    <tr>
                        <td>6</td><td>7</td><td>8</td><td>9</td><td>10</td><td>11</td><td>12</td>
                    </tr>
                    <tr>
                        <td>13</td><td>14</td><td>15</td><td>16</td><td>17</td><td>18</td><td>19</td>
                    </tr>
                    <tr>
                        <td>20</td><td>21</td><td>22</td><td>23</td><td>24</td><td>25</td><td>26</td>
                    </tr>
                    <tr>
                        <td>27</td><td>28</td><td>29</td><td>30</td><td>31</td><td></td><td></td>
                    </tr>
                </table>
            </div>

            <div class="div5">
                <h3>Reminders</h3>
                <ul>
                    <li>Submit assignment by July 14</li>
                    <li>Book counseling session</li>
                    <li>Mental Health Workshop: July 20</li>
                </ul>
            </div>

            <div class="div6">
                <h3>Helpful Resources</h3>
                <ul>
                    <li><a href="" target="_blank">Student Wellness Services</a></li>
                    <li><a href="" target="_blank">Study Support & Tutoring</a></li>
                    <li><a href="" target="_blank">Time Management Tools</a></li>
                    <li><a href="" target="_blank">Mental Health & Counseling</a></li>
                </ul>
            </div>

            <div class="div7">
                <h3>Profile</h3>
                <img src="https://static.vecteezy.com/system/resources/previews/018/765/757/original/user-profile-icon-in-flat-style-member-avatar-illustration-on-isolated-background-human-permission-sign-business-concept-vector.jpg" alt="Profile Image" class="profile-img">
            </div>

            <div class="div8">
                <h2>Contact Student Wellness</h2>
                <div class="contact-details">
                    <p><strong>Email:</strong> <a href="mailto:wellness@university.edu">wellness@university.edu</a></p>
                    <p><strong>Phone:</strong> <a href="tel:+27111234567">+27 11 123 4567</a></p>
                    <p><strong>Address:</strong> Student Wellness Centre, University Main Campus, 123 University Rd, Johannesburg</p>
                </div>
            </div>
        </div>
    </body>
</html>

# final-pro
Final Project RnH Monitoring

User Guide - 

A.1 Client - Server setup

1. Establish connection
Steps to establish a connection between the server and the client machines:
(a) Install openssh server on all the machines using following command
sudo apt-get install openssh-server
(b) After openssh is installed, start the service by entering the following command
sudo service ssh start
(c) Generate a key using rsa, these keys will be used for passwordless authentication.
ssh-keygen -t rsa
(d) Copy the public key of every client machine to server machine.
ssh-copy-id hostname@ipaddress of server machine
The above steps need to be done for all client machines individually.

2. Download and install packages
After you have established a connection, the next step is to download and install all the essential packages which are required for smooth functioning of the
application. Steps to be followed :
(a) Download the installation scripts.
(b) Run this script on every client machine using the command :
bash installpackage.sh

A.2 Server machine setup - 

Install following softwares to run the project on the server machine.
1. Angular cli
Following are the steps to download and install Angular cli :
• Install angular cli - npm install -g @angular/cli
• Create workspace and initial app - ng new “app-name”
• You will be asked to include some features in your application.
• Run the application - cd “app-name”
• ng serve –open

2. Spring Boot sts tool
• Download Spring Tool Suite from https://spring.io/tools3/sts/all.
• Extract the zip file and install the STS - sts-bundle -> sts-3.9.9.RELEASE
-> Double-click on the STS.exe.
• You will get a window, select the workspace location and click on ‘Launch’.

3. Chartjs in Angular
• Install ng-chartjs using npm - npm install ng-chartjs –save
• Now you need to install Chart.js library in your application -
npm install chart.js –save
npm install @types/chart.js –save

A3. Application Guide – 

1.	Select the hostname of the client machine which you wish to monitor from the list of the client machines. Click either Realtime analysis button or historic analysis button as per your requirement.
2.	When the Realtime analysis button is clicked on home page, the landing page displays the summary of the various parameters of the hostname selected on the previous page. The hostname can be changed, if you wish to monitor some other machine.
3.	The "Historic analysis" button allows you to get the historic analysis of certain parameters of that particular machine.
4.	The side panel consists of the various parameter tabs. You can navigate to a particular tab in order to have a detailed overview of that parameter of the machine.
5.	When the Historic Analysis button is clicked, the landing page asks you to select the time range over which you want the reports to be generated.
6.	You can change the machine you want to monitor by clicking the change hostname button.
7.	Once you select the time range, historic data charts are generated and displayed for your analysis.
8.	An additional feature provided by the application is that an alert notification is sent to the email address of the support team members whenever any parameter of any machine in the infrastructure reaches the critical limit.


CREATE TABLE EMP_Data (
    EMP_No INT,
    EMP_Name VARCHAR(100),
    EMP_Exp INT,
    EMP_Department VARCHAR(100),
    EMP_Salary INT
);

-- Insert data into EMP_Data table
INSERT INTO EMP_Data (EMP_No, EMP_Name, EMP_Exp, EMP_Department, EMP_Salary)
VALUES (101, 'Alice Johnson', 5, 'Finance', 60000);

INSERT INTO EMP_Data (EMP_No, EMP_Name, EMP_Exp, EMP_Department, EMP_Salary)
VALUES (102, 'Bob Smith', 3, 'IT', 55000);

INSERT INTO EMP_Data (EMP_No, EMP_Name, EMP_Exp, EMP_Department, EMP_Salary)
VALUES (103, 'Charlie Lee', 7, 'HR', 62000);

INSERT INTO EMP_Data (EMP_No, EMP_Name, EMP_Exp, EMP_Department, EMP_Salary)
VALUES (104, 'Diana Prince', 10, 'Marketing', 75000);

INSERT INTO EMP_Data (EMP_No, EMP_Name, EMP_Exp, EMP_Department, EMP_Salary)
VALUES (105, 'Ethan Hunt', 2, 'Operations', 50000);

INSERT INTO EMP_Data (EMP_No, EMP_Name, EMP_Exp, EMP_Department, EMP_Salary)
VALUES (106, 'Ethan Hunt', 2, 'Operations', 50000);


SELECT *
FROM EMP_Data
WHERE EMP_Department IN (
    SELECT EMP_Department
    FROM EMP_Data
    GROUP BY EMP_Department
    HAVING COUNT(*) > 1
);
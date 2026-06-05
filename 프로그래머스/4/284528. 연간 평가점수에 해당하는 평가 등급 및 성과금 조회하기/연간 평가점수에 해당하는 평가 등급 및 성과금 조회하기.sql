-- 코드를 작성해주세요
SELECT
    A.EMP_NO,
    A.EMP_NAME,
    A.GRADE,
    CASE
        WHEN GRADE = 'S' THEN A.SAL * 0.2
        WHEN GRADE = 'A' THEN A.SAL * 0.15
        WHEN GRADE = 'B' THEN A.SAL * 0.1
        ELSE 0
    END AS BONUS
FROM
(
    SELECT
        HE.EMP_NO AS EMP_NO,
        HE.EMP_NAME AS EMP_NAME,
        CASE
            WHEN HG.SCORE >= 96 THEN 'S'
            WHEN HG.SCORE >= 90 THEN 'A'
            WHEN HG.SCORE >= 80 THEN 'B'
            ELSE 'C'
        END AS GRADE,
        HE.SAL AS SAL
    FROM
        HR_EMPLOYEES AS HE
    JOIN
        (
            SELECT
                EMP_NO,
                SUM(SCORE) / 2 AS SCORE
            FROM
                HR_GRADE
            WHERE YEAR = 2022
            GROUP BY
                EMP_NO
        ) AS HG
        ON HE.EMP_NO = HG.EMP_NO
) AS A
ORDER BY EMP_NO ASC

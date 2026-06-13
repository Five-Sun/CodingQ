SELECT GRADE, ID, EMAIL
FROM (
    -- 1. 내부 쿼리: 각 개발자의 GRADE를 먼저 계산합니다 (JOIN/GROUP BY 제거로 성능 최적화)
    SELECT
        CASE 
            WHEN (D.SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE CATEGORY = 'Front End')) > 0 
                 AND (D.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'Python')) > 0 
            THEN 'A'
            WHEN (D.SKILL_CODE & (SELECT CODE FROM SKILLCODES WHERE NAME = 'C#')) > 0 
            THEN 'B'
            WHEN (D.SKILL_CODE & (SELECT SUM(CODE) FROM SKILLCODES WHERE CATEGORY = 'Front End')) > 0 
            THEN 'C' 
        END AS GRADE,
        D.ID,
        D.EMAIL
    FROM DEVELOPERS D
) SUB
-- 2. 외부 쿼리: 계산된 GRADE를 바깥에서 마음대로 필터링하고 정렬합니다.
WHERE GRADE IS NOT NULL
ORDER BY GRADE ASC, ID ASC;
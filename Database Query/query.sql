SELECT u.user_id, u.username, td.training_id , td.training_date , COUNT(td.training_id)
FROM "user" u
INNER JOIN training_details td 
ON u.user_id = td.user_id 
GROUP BY u.user_id, td.training_id, td.training_date
HAVING COUNT(td.training_id) > 1
ORDER BY td.training_date DESC;
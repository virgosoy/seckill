-- 秒杀的存储过程
-- 控制台的 ; 转成 $$
DELIMITER $$
-- 定义存储过程
-- 参数：
--
-- row_count() ：返回上一条修改类型的SQL（UPDATE，DELETE，INSERT）操作所影响的行数。
CREATE PROCEDURE seckill.execute_seckill
  (in v_seckill_id INT ,in v_user_phone VARCHAR(20),
    in v_kill_time TIMESTAMP, out r_result INT )
  BEGIN
    DECLARE insert_count int DEFAULT 0;
    START TRANSACTION;
    INSERT ignore INTO success_killed(seckill_id,user_phone,create_time)
      VALUES (v_seckill_id,v_user_phone,v_kill_time);
    SELECT row_count() INTO insert_count;
    IF (insert_count = 0) THEN
      ROLLBACK;
      set r_result = -1;
    ELSEIF (insert_count < 0) THEN
      ROLLBACK;
      set r_result = -2;
    ELSE
      UPDATE seckill
        SET number = number - 1
        WHERE seckill_id = v_seckill_id
          AND number > 0
          AND v_kill_time BETWEEN start_time AND end_time;
      SELECT row_count() INTO insert_count;
      IF (insert_count = 0) THEN
        ROLLBACK ;
        SET r_result = 0;
      ELSEIF (insert_count < 0) THEN
        ROLLBACK ;
        SET r_result = -2;
      ELSE
        COMMIT ;
        SET r_result = 1;
      END IF;
    END IF;
  END;
$$
-- 存储过程定义结束
DELIMITER ;

-- 测试执行
SET @r_result = -3;
CALL execute_seckill(1002,'12314123123',now(),@r_result);
-- 获取结果
SELECT @r_result;


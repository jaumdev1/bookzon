DO
$$
BEGIN
   IF NOT EXISTS (SELECT FROM pg_database WHERE datname = 'bookzon') THEN
      CREATE DATABASE bookzon;
   END IF;
END
$$;
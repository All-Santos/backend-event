CREATE TABLE(
     id UUID DEFAUT gen_random_uuid() PRIMARY KEY,
     city VARCHAR(100) NOT NULL,
     uf VARCHAR(100) OT NULL,
     event_id UUID,
     FOREIGN KEY (event_id) REFERENCES event(id) ON DELETE CASCADE
     
);

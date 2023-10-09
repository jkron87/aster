CREATE TABLE meeting_note (
    id INT AUTO_INCREMENT PRIMARY KEY,
    meeting_id INT NOT NULL,
    text TEXT NOT NULL,
    FOREIGN KEY (meeting_id) REFERENCES meeting(id)
);

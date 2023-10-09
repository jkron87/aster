CREATE TABLE meeting_participant (
    meeting_id INT NOT NULL,
    user_id INT NOT NULL,
    PRIMARY KEY (meeting_id, user_id),
    FOREIGN KEY (meeting_id) REFERENCES meeting(id),
    FOREIGN KEY (user_id) REFERENCES user(id)
);
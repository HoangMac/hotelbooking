CREATE TABLE customer (
    id VARCHAR(255) PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    middle_name VARCHAR(255),
    last_name VARCHAR(255) NOT NULL,
    username VARCHAR(32) NOT NULL,
    status VARCHAR(50) NOT NULL,
    email VARCHAR(255),
    cellphone VARCHAR(255) NOT NULL,
    date_of_birth DATE NOT NULL,
    version INTEGER NOT NULL DEFAULT 0,
    created_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
    modified_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE payment_method (
    id VARCHAR(255) PRIMARY KEY,
    customer_id VARCHAR(255) NOT NULL,
    type VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    is_default BOOLEAN NOT NULL,
    info VARCHAR(255),
    version INTEGER NOT NULL DEFAULT 0,
    created_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
    modified_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE reservation_order (
    id VARCHAR(255) PRIMARY KEY,
    customer_id VARCHAR(255) NOT NULL,
    ref_no VARCHAR(255) NOT NULL,
    status VARCHAR(255) NOT NULL,
    check_in_date DATE NOT NULL,
    check_in_time TIME,
    check_out_date DATE NOT NULL,
    check_out_time TIME,
    payment_method_id VARCHAR(255),
    room_code VARCHAR(255) NOT NULL,
    guest_count INT NOT NULL,
    payment_status VARCHAR(255) NOT NULL,
    version INTEGER NOT NULL DEFAULT 0,
    created_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
    modified_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;


CREATE TABLE room_info (
    code VARCHAR(255) PRIMARY KEY,
    max_capacity INT NOT NULL,
    description VARCHAR(255),
    is_locked TINYINT(1),
    version INTEGER NOT NULL DEFAULT 0,
    created_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3),
    modified_date TIMESTAMP(3) DEFAULT CURRENT_TIMESTAMP(3)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE INDEX idx__payment_method__customer_id ON payment_method(customer_id);
CREATE INDEX idx__reservation_order__customer_id ON reservation_order(customer_id);




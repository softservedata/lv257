@org.hibernate.annotations.GenericGenerator(
        name = Constants.ID_GENERATOR,
        strategy = "enhanced-sequence",
        parameters = {
                @org.hibernate.annotations.Parameter(
                        name = "sequence_name",
                        value = Constants.ID_GENERATOR_SEQUENCE_NAME
                ),
                @org.hibernate.annotations.Parameter(
                        name = "initial_value",
                        value = "100000"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "increment_size",
                        value = "100"
                ),
                @org.hibernate.annotations.Parameter(
                        name = "optimizer",
                        value = "pooled-lo"
                )

        })
package com.softserve.edu.Resources.entity;

import com.softserve.edu.Resources.Constants;

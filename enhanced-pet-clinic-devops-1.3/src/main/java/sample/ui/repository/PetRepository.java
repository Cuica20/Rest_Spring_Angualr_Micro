/*
 * Copyright 2002-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package sample.ui.repository;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;

import sample.ui.model.BaseEntity;
import sample.ui.model.Pet;

/**
 * Repository class for <code>Pet</code> domain objects All method names are
 * compliant with Spring Data naming conventions so this interface can easily be
 * extended for Spring Data See here:
 * <pre>
 * http://static.springsource.org/spring-data/jpa/docs/current/reference/html/jpa.repositories.html#jpa.query-methods.query-creation
 * </pre>
 *
 * @author Ken Krebs
 * @author Juergen Hoeller
 * @author Sam Brannen
 * @author Michael Isvy
 * @author Arnaldo Piccinelli
 */
public interface PetRepository extends Repository<Pet, Long> {

    /**
     * Retrieve a <code>Pet</code> from the data store by id.
     *
     * @param id the id to search for
     * @return the <code>Pet</code> if found
     * @throws org.springframework.dao.DataRetrievalFailureException
     *          if not found
     */
    Pet findById(Long id) throws DataAccessException;

    /**
     * Save a <code>Pet</code> to the data store, either inserting or updating it.
     *
     * @param pet the <code>Pet</code> to save
     * @see BaseEntity#isNew
     */
    void save(Pet pet) throws DataAccessException;

}

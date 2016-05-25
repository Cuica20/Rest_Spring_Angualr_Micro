/*
 * Copyright 2014 the original author or authors.
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

package org.javier.rest.web;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

import org.javier.rest.model.Game;

@Component
final class GameResourceAssembler implements ResourceAssembler<Game, Resource<Game>> {

    @Override
    public Resource<Game> toResource(Game game) {
        Resource<Game> resource = new Resource<Game>(game);
        // TODO 3: link to /games/{gameId}/doors, rel: doors
        // TODO 3: link to /games/{gameId}, rel: self
        resource.add(linkTo(GamesController.class).slash(game.getId()).slash("doors").withRel("doors"));
        resource.add(linkTo(GamesController.class).slash(game.getId()).withSelfRel());
        return resource;
    }

}

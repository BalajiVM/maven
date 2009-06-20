package org.apache.maven.model;

/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Collects the output of the model builder.
 * 
 * @author Benjamin Bentmann
 */
class DefaultModelBuildingResult
    implements ModelBuildingResult
{

    private Model effectiveModel;

    private List<String> modelIds;

    private Map<String, Model> rawModels;

    private Map<String, List<Profile>> activePomProfiles;

    private List<Profile> activeExternalProfiles;

    public DefaultModelBuildingResult()
    {
        modelIds = new ArrayList<String>();
        rawModels = new HashMap<String, Model>();
        activePomProfiles = new HashMap<String, List<Profile>>();
        activeExternalProfiles = new ArrayList<Profile>();
    }

    public Model getEffectiveModel()
    {
        return effectiveModel;
    }

    public DefaultModelBuildingResult setEffectiveModel( Model model )
    {
        this.effectiveModel = model;

        return this;
    }

    public List<String> getModelIds()
    {
        return Collections.unmodifiableList( modelIds );
    }

    public DefaultModelBuildingResult addModelId( String modelId )
    {
        if ( modelId == null )
        {
            throw new IllegalArgumentException( "no model identifier specified" );
        }

        modelIds.add( modelId );

        return this;
    }

    public Model getRawModel()
    {
        return rawModels.get( modelIds.get( 0 ) );
    }

    public Model getRawModel( String modelId )
    {
        return rawModels.get( modelId );
    }

    public DefaultModelBuildingResult setRawModel( String modelId, Model rawModel )
    {
        if ( modelId == null )
        {
            throw new IllegalArgumentException( "no model identifier specified" );
        }

        rawModels.put( modelId, rawModel );

        return this;
    }

    public List<Profile> getActivePomProfiles( String modelId )
    {
        List<Profile> profiles = this.activePomProfiles.get( modelId );
        return ( profiles != null ) ? Collections.unmodifiableList( profiles ) : null;
    }

    public DefaultModelBuildingResult setActivePomProfiles( String modelId, List<Profile> activeProfiles )
    {
        if ( modelId == null )
        {
            throw new IllegalArgumentException( "no model identifier specified" );
        }

        if ( activeProfiles != null )
        {
            this.activePomProfiles.put( modelId, new ArrayList<Profile>( activeProfiles ) );
        }
        else
        {
            this.activePomProfiles.remove( modelId );
        }

        return this;
    }

    public List<Profile> getActiveExternalProfiles()
    {
        return Collections.unmodifiableList( activeExternalProfiles );
    }

    public DefaultModelBuildingResult setActiveExternalProfiles( List<Profile> activeProfiles )
    {
        this.activeExternalProfiles.clear();

        if ( activeProfiles != null )
        {
            this.activeExternalProfiles.addAll( activeProfiles );
        }

        return this;
    }

}
import React from 'react';
import Grid from '@material-ui/core/Grid';

import AddMenuForm from './AddMenuForm';
import SubmitMenuForm from './SubmitMenuForm';
import MenuFormList from './MenuFormList';

const CreateMenu = function () {
  return (
    <div>
      <Grid container>
        <Grid item xs={4}>
          <AddMenuForm />
        </Grid>
        <Grid item xs={4}>
          <SubmitMenuForm />
        </Grid>
        <Grid item xs={12}>
          <MenuFormList />
        </Grid>
      </Grid>
    </div>
  )
}

export default CreateMenu;

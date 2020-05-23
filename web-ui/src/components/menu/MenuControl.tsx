import React from 'react';
import Grid from '@material-ui/core/Grid';

import MenuInfoList from './MenuInfoList';

const MenuControl = function () {
  return (
    <div>
      <Grid container>
        <Grid item xs={12}>
          <MenuInfoList />
        </Grid>
      </Grid>
    </div>
  )
}

export default MenuControl;

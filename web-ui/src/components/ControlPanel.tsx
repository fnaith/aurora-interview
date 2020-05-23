import React, { useState } from 'react';

import global_data from '../store/global_data';

import SelectTabs from './SelectTabs';
import SelectTab from './SelectTab';
import SelectPanel from './SelectPanel';
import CreateMenu from './menu/CreateMenu';
import MenuControl from './menu/MenuControl';
import UserInfoList from './user/UserInfoList';
import Logout from './Logout';

const ControlPanel = function () {
  const [flag, setFlag] = useState(true);
  global_data.updateControlPanel = () => setFlag(!flag);
  global_data.init();

  const [tabValue, setTabValue] = useState(0);

  const handleChange = (event: React.ChangeEvent<{}>, value: number) => {
    setTabValue(value);
  };

  return (
    <div>
      <SelectTabs
        value={tabValue}
        onChange={handleChange}
        variant="scrollable"
        scrollButtons="auto"
        indicatorColor="primary"
        textColor="primary"
      >
        <SelectTab label="Create Menu" key={'select-tab-create-menust'}/>
        <SelectTab label="Menu Control" key={'select-tab-menust-control'}/>
        <SelectTab label="User Info" key={'select-tab-menust-control'}/>
        <SelectTab label="Option" key={'select-tab-option'}/>
        <SelectTab label="Github" key={'select-tab-github'}/>
      </SelectTabs>
      <SelectPanel expanded={tabValue === 0}>
        <CreateMenu />
      </SelectPanel>
      <SelectPanel expanded={tabValue === 1}>
        <MenuControl />
      </SelectPanel>
      <SelectPanel expanded={tabValue === 2}>
        <UserInfoList />
      </SelectPanel>
      <SelectPanel expanded={tabValue === 3}>
        <Logout />
      </SelectPanel>
      <SelectPanel expanded={tabValue === 4}>
        <a href="https://github.com/fnaith/aurora-interview">Link</a>
      </SelectPanel>
    </div>
  )
}

ControlPanel.whyDidYouRender = true;

export default ControlPanel;
